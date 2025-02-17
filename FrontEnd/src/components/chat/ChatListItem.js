import React from 'react';
import Box from '@mui/joy/Box';
import ListDivider from '@mui/joy/ListDivider';
import ListItem from '@mui/joy/ListItem';
import ListItemButton from '@mui/joy/ListItemButton';
import Stack from '@mui/joy/Stack';
import Typography from '@mui/joy/Typography';
import CircleIcon from '@mui/icons-material/Circle';
import AvatarWithStatus from './AvatarWithStatus';
import { toggleMessagesPane } from '../../utils/chat/utils';
import { useState } from 'react';

export default function ChatListItem(props) {
  const { id, sender, messages, selectedChatId, setSelectedChat, type } = props;
  const selected = selectedChatId === id;
  {
    return (
        <React.Fragment>
          <ListItem>
            <ListItemButton
                onClick={() => {
                  toggleMessagesPane();
                  setSelectedChat({ id, sender, messages, type });
                }}
                selected={selected}
                color="neutral"
                sx={{ flexDirection: 'column', alignItems: 'initial', gap: 1 }}
            >
              <Stack direction="row" spacing={1.5}>
                <AvatarWithStatus online={sender.online} src={sender.avatar} />
                <Box sx={{ flex: 1 }}>
                  <Typography level="title-sm">{sender.name}</Typography>
                  <Typography level="body-sm">{sender.username}</Typography>
                </Box>
                <Box sx={{ lineHeight: 1.5, textAlign: 'right' }}>
                  {messages[0].unread && (
                      <CircleIcon sx={{ fontSize: 12 }} color="primary" />
                  )}
                  <Typography
                      level="body-xs"
                      noWrap
                      sx={{ display: { xs: 'none', md: 'block' } }}
                  >
                    {`${Math.floor(((new Date()) - Date.parse(messages[messages.length - 1].timestamp)) / 60000)} 분 전`}
                  </Typography>
                </Box>
              </Stack>
              <Typography
                  level="body-sm"
                  sx={{
                    display: '-webkit-box',
                    WebkitLineClamp: '2',
                    WebkitBoxOrient: 'vertical',
                    overflow: 'hidden',
                    textOverflow: 'ellipsis',
                  }}
              >
                {messages[0].content}
              </Typography>
            </ListItemButton>
          </ListItem>
          <ListDivider sx={{ margin: 0 }} />
        </React.Fragment>
    );
  }

}