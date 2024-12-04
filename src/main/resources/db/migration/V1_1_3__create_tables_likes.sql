CREATE TABLE IF NOT EXISTS posts.user_post_likes(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users.users(id),
    post_id INTEGER REFERENCES posts.posts(id)
);

CREATE TABLE IF NOT EXISTS posts.user_comment_likes(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users.users(id),
    comment_id INTEGER REFERENCES posts.comments(id)
);