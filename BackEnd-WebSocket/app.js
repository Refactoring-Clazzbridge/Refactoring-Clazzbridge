const express = require('express');
const { createServer } = require('node:http');
const { Server } = require('socket.io');
const cors = require('cors');
const { verifyToken } = require('./src/auth');
const { initializeRedis } = require('./src/redisClient');
const { handleChatMessages, getAllChatsFromUserId, setUserOnlineStatus,
  newMessage, fetchUserData, getUserCourseId, getUsersByRoleOrCourse,
  getUsersByCourse, getUserData
} = require('./src/chatService');
const { join } = require('node:path');
const requestWithAuthToken = require('./src/apiClient');
const {fetchDataFromMySQL} = require("./src/fetchDataFromMySQL");
const {socketEventRegist} = require("./src/socketEvents");
const {initializeIo} = require("./src/ioConfig");
require("dotenv").config();

const app = express();
const host = process.env.HOST || '127.0.0.1';
const port = process.env.CHAT_PORT || 3001;

app.use(cors({
  origin: [`http://${host}:${port}`, `http://${host}:3001`],
  methods: ['GET', 'POST'],
  allowedHeaders: ['Content-Type', 'Authorization'],
}));

async function startServer() {
  try {
    await initializeRedis();
    await fetchDataFromMySQL();

    const server = createServer(app);
    initializeIo(server);

    server.listen(port, host, () => {
      console.log(`Server is running on http://${host}:${port}`);
    });
  } catch (error) {
    console.error('Failed to start server:', error);
    process.exit(1);
  }
}

startServer();