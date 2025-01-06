import axios from "../../../shared/apiClient";

export const getBoardType = async () => {
  try {
    const response = await axios.get("boardTypes");
    return response.data;
  } catch (error) {
    console.error("Error fetching board:", error);
    throw error;
  }
};
