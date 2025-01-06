import React from "react";
import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ element, isLoggedIn }) => {
  return isLoggedIn ? element : <Navigate to="/" />;
};

export default ProtectedRoute;

// 로그인된 사용자가 /login으로 접근했을 때, 대시보드나 다른 페이지로 리다이렉트하는 방법
