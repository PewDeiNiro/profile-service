ALTER TABLE posts.posts ADD COLUMN type VARCHAR;
ALTER TABLE posts.posts ADD COLUMN original_post_id INTEGER REFERENCES posts.posts(id);