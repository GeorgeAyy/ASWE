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
        'confirm': '#31c07e',
      },
      keyframes: {
        pulse: {
          '0%': { transform: 'scale(1)' },
          '50%': { transform: 'scale(1.1)' },
          '100%': { transform: 'scale(1)' },
        },
        shake: {
          '0%, 100%': { transform: 'translateX(0)' },
          '25%, 75%': { transform: 'translateX(-10px)' },
          '50%': { transform: 'translateX(10px)' },
        }
      },
      animation: {
        pulse: 'pulse 2s infinite',
        shake: 'shake 0.5s',
      }
    },
  },
  plugins: [],
  darkMode: "class"
}