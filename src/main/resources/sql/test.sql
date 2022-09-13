select uc.chat_id
from users u
join users_chat uc
    on u.id = uc.user_id
where u.id = 1;
