CREATE TABLE IF NOT EXISTS posts.walls(
     id SERIAL PRIMARY KEY
);

ALTER TABLE posts.posts ADD COLUMN wall_id INTEGER REFERENCES posts.walls(id);
ALTER TABLE users.users ADD COLUMN wall_id INTEGER REFERENCES posts.walls(id);