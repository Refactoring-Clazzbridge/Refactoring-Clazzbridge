import Modal from "@mui/material/Modal";
import Paper from "@mui/material/Paper";

function CustomModal({ isOpen, closeModal, children }) {
  return (
    <Modal open={isOpen} onClose={closeModal}>
      <Paper
        elevation={2}
        sx={{
          position: "absolute",
          borderRadius: "8px",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
          width: 600,
          minHeight: 200,
          maxWidth: "100%",
          maxHeight: "90%",
          overflowY: "auto",
          padding: "26px",
        }}
      >
        {children}
      </Paper>
    </Modal>
  );
}

export default CustomModal;
