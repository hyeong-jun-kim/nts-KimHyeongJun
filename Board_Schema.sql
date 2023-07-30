CREATE TABLE `post` (
  `post_id` int PRIMARY KEY,
  `title` varchar(30),
  `writer` varchar(10),
  `content` varchar(500),
  `password` text,
  `viewCount` int,
  `created_at` timestamp,
  `updated_at` timestamp,
  `status` varchar(10)
);

CREATE TABLE `comment` (
  `comment_id` int PRIMARY KEY,
  `post_id` int,
  `parent_comment_id` Integer,
  `writer` varchar(10),
  `content` varchar(100),
  `password` text,
  `create_at` timestamp,
  `update_at` timestamp,
  `status` varchar(10)
);

CREATE TABLE `like` (
  `like_id` int PRIMARY KEY,
  `post_id` int,
  `likeType` varchar(6),
  `ip` varchar(45),
  `create_at` timestamp,
  `update_at` timestamp,
  `status` varchar(10)
);

CREATE TABLE `post_hashtag` (
  `post_hashtag_id` int PRIMARY KEY,
  `post_id` int,
  `hashtag_id` int,
  `create_at` timestamp,
  `update_at` timestamp,
  `status` varchar(10)
);

CREATE TABLE `hashtag` (
  `hashtag_id` int PRIMARY KEY,
  `post_hash_tag_id` int,
  `name` varchar(50),
  `createAt` timestamp,
  `updateAt` timestamp,
  `status` varchar(10)
);

ALTER TABLE `comment` ADD INDEX `idx_parent_comment_id` (`parent_comment_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`comment_id`) REFERENCES `comment` (`parent_comment_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `like` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `post_hashtag` ADD FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`);

ALTER TABLE `post_hashtag` ADD FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`hashtag_id`);
