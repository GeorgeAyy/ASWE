/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,js}",
    "./node_modules/tw-elements/js/**/*.js"
  ],
  
  purge: [
    "./src/**/*.{html,js}",
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
  plugins: [require("tw-elements/plugin.cjs")],
  darkMode: "class"
}

