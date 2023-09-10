module.exports = [
    {
        context: [
            // REST API
            "/api",
            // OAuth2
            "/oauth2",
            "/login/oauth2/code",
            "/logout",
        ],
        target: "http://localhost:8080",
        secure: false,
    },
];
