import React from 'react';
import {Switch, Route} from 'react-router-dom';

import {routes} from "./routes";

export default function Router() {
    return (
        <div>
            <Switch>
                {routes.map(({path, Component, layout}) => (
                    <Route exact path={path} key={path} render={props => {
                        if (layout === 'default') {
                            return (
                                <div>
                                        {/* eslint-disable-next-line react/jsx-props-no-spreading */}
                                        <Component {...props} />
                                </div>);
                        } else {
                            return (
                                <div>
                                        {/* eslint-disable-next-line react/jsx-props-no-spreading */}
                                        <Component {...props} />
                                </div>);
                        }
                    }}
                    />
                ))}
            </Switch>
        </div>
    );
}
