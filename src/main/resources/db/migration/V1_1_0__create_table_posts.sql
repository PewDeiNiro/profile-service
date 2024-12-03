CREATE SCHEMA IF NOT EXISTS posts;

CREATE TABLE IF NOT EXISTS posts.posts(
    id SERIAL PRIMARY KEY,
    text VARCHAR,
    author_id INTEGER REFERENCES users.users(id)
);

CREATE TABLE IF NOT EXISTS posts.comments(
    id SERIAL PRIMARY KEY,
    text VARCHAR,
    post_id INTEGER REFERENCES posts.posts(id),
    author_id INTEGER REFERENCES users.users(id)
);