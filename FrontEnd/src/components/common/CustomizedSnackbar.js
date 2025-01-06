import * as React from "react";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";

export default function CustomizedSnackbar(isSnackOpen, closeSnack, children) {
  return (
    <div>
      <Snackbar open={isSnackOpen} autoHideDuration={5000} onClose={closeSnack}>
        <Alert
          onClose={closeSnack}
          severity="success"
          variant="filled"
          sx={{ width: "100%" }}
        >
          {children}
        </Alert>
      </Snackbar>
    </div>
  );
}
