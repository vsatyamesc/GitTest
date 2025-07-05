import React from "react";

const PostDetail = ({ post, onBack }) => {
  if (!post) {
    return null;
  }

  return (
    <div className="p-6 bg-white">
      <button
        onClick={onBack}
        className="mb-4 text-sm font-medium text-blue-600 hover:text-blue-800"
        aria-label="Back to post list"
      >
        Close
      </button>
      <h2 className="text-3xl font-bold mb-2">{post.title}</h2>
      <p className="text-gray-700">{post.content}</p>
    </div>
  );
};

export default PostDetail;
