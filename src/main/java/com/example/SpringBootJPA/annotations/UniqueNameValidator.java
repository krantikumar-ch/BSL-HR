package com.example.SpringBootJPA.annotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;


@SuppressWarnings({"rawtypes","unchecked"})
public class UniqueNameValidator implements ConstraintValidator<UniqueChild, Object> {


	private UniqueChild uniqueName;
	
	@Autowired
	private EntityManager entityManager;
	

	@Override
	public void initialize(UniqueChild constraintAnnotation) {
		this.uniqueName = constraintAnnotation;
	}

	
	/* 
	 * Used this method to validate given entity object is unique w.r.t unique elements
	 * in database
	 * Inside we used JPA criteria query to get values in database
	 * Reflection api to get values from the object
	 * returns boolean
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context){
		
		try{
			Class entityClass= value.getClass();
			String[] uniqueKeys = uniqueName.uniqueKeys();
			
			CriteriaBuilder cBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery criteria = cBuilder.createQuery(entityClass);
			Root qry = criteria.from(entityClass);
			
			List<Predicate> predicates = new ArrayList<>(uniqueKeys.length);
			
			for(int i=0; i< uniqueKeys.length; i++){
				String key = uniqueKeys[i];
				Object methodValue = getMethodValue(entityClass, value, uniqueKeys[i]);
				if(methodValue !=null){
					methodValue = methodValue instanceof String ? methodValue.toString().toUpperCase() : methodValue;
					predicates.add( cBuilder.equal(cBuilder.upper(qry.get(key)),  methodValue));
				}
				
			}
			
			criteria.where(cBuilder.and(predicates.stream().toArray(Predicate[]::new)));	
			TypedQuery tq =entityManager.createQuery(criteria.select(qry));
			List l = tq.getResultList();
			
			String primary = uniqueName.primaryKey();
			
			Object methodValue = getMethodValue(entityClass, value, primary);
			
			return methodValue != null && l.size() ==1 ? true : l.isEmpty() ? true : false;
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Object getMethodValue(Class entityClass, Object value, String key) throws Exception{
		
		String getMethod = "get"+((key.charAt(0)+"").toUpperCase())+(key.substring(1));
		Object methodValue = entityClass.getMethod(getMethod).invoke(value);
		return methodValue;
	}

	

}
