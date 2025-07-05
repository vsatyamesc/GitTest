import React from "react";

const PostList = ({ posts, onPostSelect }) => {
  if (!posts || posts.length === 0) {
    return <p>No posts available.</p>;
  }
  const truncatedContent = (content) => {
    return content.slice(0, Math.min(content.length, 200));
  };
  return (
    <ul className="space-y-4 px-5">
      {posts.map((post) => (
        <li key={post.id}>
          <div className="flex flex-col justify-items-start border-1 p-2 rounded-md border-blue-700">
            <p className="flex justify-between">
              <span
                className="text-2xl font-semibold text-blue-600 text-left"
                aria-label={`View post: ${post.title}`}
              >
                {post.id}. {post.title}
              </span>
              <span className="text-[#8a8a8a] text-sm">By {post.author}</span>
            </p>

            <p className="text-lg text-black font-normal flex justify-between">
              <span className="max-w-[65vw]">{truncatedContent(post.content)}...</span>
              <span className="text-[#8a8a8a] text-sm">On {post.date}</span>
            </p>
            <button onClick={() => onPostSelect(post)}>
              <p className="text-blue-600 text-md text-left">Read More</p>
            </button>
          </div>
        </li>
      ))}
    </ul>
  );
};

export default PostList;
