import React, { useState } from "react";
import PostList from "../components/PostList";
import PostDetail from "../components/PostDetail";

export async function getStaticProps() {
  const res = await fetch("http://localhost:3000/api/posts");
  const posts = await res.json();

  if (!posts) {
    return {
      notFound: true,
    };
  }

  return {
    props: {
      posts,
    },
    revalidate: 10,
  };
}

const PostsPage = ({ posts }) => {
  const [selectedPost, setSelectedPost] = useState(null);

  const handlePostSelect = (post) => {
    setSelectedPost(post);
  };

  const handleBack = () => {
    setSelectedPost(null);
  };

  return (
    <div className="h-[100vh] flex w-[100vw] px-[10vw] bg-gray-50">
      <div className="md:px-8 w-[100%] h-[100%]">
        <div className="bg-white w-[100%] h-[100%]">
          <h1 className="text-4xl font-bold text-center mb-8 text-gray-800 pt-6">
            Blog Posts
          </h1>
          {selectedPost ? (
            <PostDetail post={selectedPost} onBack={handleBack} />
          ) : (
            <PostList posts={posts} onPostSelect={handlePostSelect} />
          )}
        </div>
      </div>
    </div>
  );
};

export default PostsPage;
