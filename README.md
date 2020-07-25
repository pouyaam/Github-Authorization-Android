# Github Login
## Overview
**Github Login** is a simple application which uses github oauth to access github APIs. User can search any repositories and see it's commits in addition to to review his/her github profile.

## Technical Overview
The app is developed upon Clean + MVVM architecture. it has two data sources : 
1. Remote data source which is based on [Github API v3](https://developer.github.com/v3/). first user login in to app using his/her github account then uses this api to search repos, review commits and his/her profile.
2. Offline data source which stores user's authentication status

worth metioning that both data sources are **unit tested**, in addition to all viewmodels

## Design
Icons are from AndroidStudio built-in Material Icon pack. The illustration icons are from [iconfinder.com](https://iconfinder.com)

## Further Developments
Further developments can include these parts:
1. Add resiliency to the app, meaning that If there is no network available when a request is due, app park the call and perform it as
soon as the network is back.
 2. add integration and UI tests
