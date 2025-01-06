import React from "react";
import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import TopBar from "./TopBar";
import SideBar from "./SideBar";
import { useSidebar } from "../../context/SidebarContext"; // Context import

const drawerWidth = 240;
const closedDrawerWidth = 64; // 슬라이드바가 닫혔을 때의 넓이

const Main = styled("main", { shouldForwardProp: (prop) => prop !== "open" })(
  ({ theme, open }) => ({
    flexGrow: 1,
    padding: theme.spacing(3),
    transition: theme.transitions.create(["margin", "width"], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    marginLeft: closedDrawerWidth,
    ...(open && {
      transition: theme.transitions.create(["margin", "width"], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      marginLeft: drawerWidth,
    }),
  })
);

const DrawerHeader = styled("div")(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  padding: theme.spacing(0, 1),
  ...theme.mixins.toolbar,
  justifyContent: "flex-end",
}));

const Layout = ({ children }) => {
  const { open, toggleSidebar } = useSidebar(); // Context에서 값 가져오기

  return (
    <Box sx={{ display: "flex", height: "100%" }}>
      <CssBaseline />
      <TopBar open={open} />
      <SideBar open={open} handleDrawerToggle={toggleSidebar} />
      <Main open={open} sx={{ overflowX: "hidden" }}>
        <DrawerHeader />
        {children}
      </Main>
    </Box>
  );
};

export default Layout;
