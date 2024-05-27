/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/templates/**/*.html",
    "./node_modules/tw-elements/js/**/*.js"
  ],
  
  purge: [
    "./src/main/resources/templates/**/*.html",,
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#5a5a5a',
        'secondary': '#999999',
        'button': '#111827',
        'button-hover': '#1f2937',
        'input': '#f9fafb',
        'input-hover': '#f3f4f6',
        'input-focus': '#e5e7eb',
      },
    },
  },
  plugins: [],
  darkMode: "class"
}