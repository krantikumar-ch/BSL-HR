import React from 'react';
import ReactDOM from 'react-dom';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter } from 'react-router-dom';
import {ToastContainer} from 'react-toastify';
import App from './js/main/App';

import 'bootstrap/dist/css/bootstrap.css';
import "font-awesome/css/font-awesome.css";
import 'react-toastify/dist/ReactToastify.css';


import './styles/index.css';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
      <ToastContainer
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable={false}
        pauseOnHover
        position="top-left"
        autoClose={4000}
        />
    </BrowserRouter>
  </React.StrictMode>,

  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
