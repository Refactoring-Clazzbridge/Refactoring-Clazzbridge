import { defineConfig } from "eslint-define-config";

export default defineConfig({
  env: {
    browser: true,
    es2021: true,
  },
  extends: [
    "eslint:recommended",
    "plugin:react/recommended",
    "google", // Google 스타일 가이드 적용
    "prettier", // Prettier와 ESLint 충돌 방지
  ],
  parserOptions: {
    ecmaVersion: "latest",
    sourceType: "module",
    ecmaFeatures: {
      jsx: true,
    },
  },
  plugins: ["react", "prettier"],
  rules: {
    "prettier/prettier": "error", // Prettier 오류를 ESLint로 처리
    "no-var": "error", // var 사용 금지
    "react/jsx-filename-extension": [1, { extensions: [".js", ".jsx"] }], // JSX 파일 확장자 설정
  },
});
