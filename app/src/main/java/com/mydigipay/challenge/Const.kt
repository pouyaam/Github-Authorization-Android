package com.mydigipay.challenge

// The base URL used for request a user's GitHub identity.
const val AUTHORIZE_URL: String = "https://github.com/login/oauth/authorize"

// The base URL used for request AccessToken from GitHub.
const val ACCESS_TOKEN_URL: String = "https://github.com/login/oauth/access_token"

// The base URL used for request AccessToken from GitHub.
const val REST_API_URL: String = "https://api.github.com"

// The URL in your application where users are sent after authorization.
const val REDIRECT_URI: String = "com.mydigipay.challenge://oauth2redirect"

// The client ID you received from GitHub for your GitHub App.
const val CLIENT_ID = "407ca6e82fb962149752"

// The client secret you received from GitHub for your GitHub App.
const val CLIENT_SECRET = "54026b111c06b89ea48bf5082ac8fce6da1480c5"
