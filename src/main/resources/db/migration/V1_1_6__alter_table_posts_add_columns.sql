ALTER TABLE posts.comments ADD COLUMN type VARCHAR;
ALTER TABLE posts.comments ADD COLUMN parent_comment_id INTEGER REFERENCES posts.comments(id);