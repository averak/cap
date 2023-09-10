/** @type {import("tailwindcss").Config} */
module.exports = {
    mode: "jit",
    prefix: "",
    darkMode: "class",
    content: ["./src/**/*.{html,ts}"],
    theme: {
        extend: {
            colors: {
                primary: "#3f51b5",
                accent: "#e91e63",
                warn: "#f44336",
                foreground: "#000000DE",
            },
        },
    },
    plugins: [],
};
