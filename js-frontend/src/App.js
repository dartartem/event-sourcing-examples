/**
 * Created by andrew on 12/02/16.
 */

import React from "react";
import { createStore, compose, applyMiddleware, combineReducers} from "redux";
import { Provider, connect} from "react-redux";
import thunk from "redux-thunk";
import createLogger from 'redux-logger';
import { Route, IndexRoute, Link, IndexLink } from "react-router";
import { ReduxRouter} from "redux-router";
import { createHistory, createHashHistory, createMemoryHistory } from "history";
import { pushState, routerStateReducer, reduxReactRouter as clientRouter} from "redux-router";
import { reduxReactRouter as serverRouter } from "redux-router/server";

import mainReducer from './reducers';

import { configure as reduxAuthConfigure } from './actions/configure';
import { requireAuthentication } from './components/AuthComponent';
import Container from "./components/partials/Container";
import MyAccounts from "./views/MyAccounts";
import Account from "./views/Account";
import SignIn from "./views/SignIn";
import SignUp from "./views/SignUp";

class App extends React.Component {
  render() {
    return (<Container>
        {this.props.children}
      </Container>);
  }
}

export function initialize({cookies, isServer, currentLocation, userAgent} = {}) {

  const reducer = combineReducers({
    app: mainReducer,
    router: routerStateReducer
  });

  const routes = (
    <Route path="/" component={App}>
      <IndexRoute component={requireAuthentication(MyAccounts)} />
      <Route path="signin" component={SignIn} />
      <Route path="register" component={SignUp} />
      <Route path="account/:accountId" component={requireAuthentication(Account)} />
    </Route>
  );

  // these methods will differ from server to client
  const reduxReactRouter    = isServer ? serverRouter : clientRouter;
  const createHistoryMethod = isServer ? createMemoryHistory : createHashHistory;

  // create the redux store
  const store = compose(
    applyMiddleware(thunk, createLogger()),
    reduxReactRouter({
      routes,
      createHistory: createHistoryMethod
    })
  )(createStore)(reducer);


  /**
   * The React Router 1.0 routes for both the server and the client.
   */
  return store.dispatch(reduxAuthConfigure([
    {
      default: {
        //apiUrl: '/',
        emailSignInPath: '/login',
        customersPath: '/customers',
        currentUserPath: '/user',
        accountsPath: '/accounts',
        transfersPath: '/transfers'
      }
    }
  ], {
    cookies,
    isServer,
    currentLocation,
    storage: 'localStorage',
    tokenFormat: {
      "access-token": "{{ access-token }}"
    },
    handleLoginResponse: function(resp) {
      debugger;

      return resp.data;
    },

    handleAccountUpdateResponse: function(resp) {
      debugger;

      return resp.data;
    },

    handleTokenValidationResponse: function(resp) {
      debugger;
      return resp.data;
    }
  })).then(({ redirectPath, blank } = {}) => {
    // hack for material-ui server-side rendering.
    // see https://github.com/callemall/material-ui/pull/2007
    if (userAgent) {
      global.navigator = {userAgent};
    }

    if (blank) {
      // if `blank` is true, this is an OAuth redirect and should not
      // be rendered
      return <noscript />;
    }

    console.log(`redirect path: ${redirectPath}`);

    return ({
      blank,
      store,
      redirectPath,
      provider: (
        <Provider store={store} key="provider">
          <ReduxRouter children={routes} />
        </Provider>
      )
    });
  });
}