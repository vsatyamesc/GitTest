import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom";
import PostsPage, { getStaticProps } from "../src/pages/index";

const mockPostsData = [
  {
    id: 1,
    title: "Post1",
    content: "Post1s Content",
    author: "A1",
    date: "2025-07-01",
  },
  {
    id: 2,
    title: "Post2",
    content: "Post2s Content",
    author: "A2",
    date: "2025-07-02",
  },
];

jest.mock("../src/components/PostList", () => {
  return ({ posts, onPostSelect }) => (
    <div>
      <h2>Post List</h2>
      {posts.map((post) => (
        <div key={post.id}>
          <p>{post.title}</p>
          <button onClick={() => onPostSelect(post)}>Select {post.id}</button>
        </div>
      ))}
    </div>
  );
});
jest.mock("../src/components/PostDetail", () => {
  return ({ post, onBack }) => (
    <div>
      <h2>Post Detail</h2>
      <p>{post.content}</p>
      <button onClick={onBack}>Close</button>
    </div>
  );
});

describe("PostsPage Component", () => {
  it("initial state: selectedPost is null, so it renders PostList", () => {
    render(<PostsPage posts={mockPostsData} />);

    expect(
      screen.getByRole("heading", { name: /Blog Posts/i })
    ).toBeInTheDocument();
    expect(
      screen.getByRole("heading", { name: /Post List/i })
    ).toBeInTheDocument();
    expect(
      screen.queryByRole("heading", { name: /Post Detail/i })
    ).not.toBeInTheDocument();
  });

  it("setSelectedPost with a value: switches to PostDetail view when a post is selected", () => {
    render(<PostsPage posts={mockPostsData} />);

    const selectButton = screen.getByRole("button", { name: /Select 1/i });
    fireEvent.click(selectButton);

    expect(
      screen.getByRole("heading", { name: /Post Detail/i })
    ).toBeInTheDocument();
    expect(screen.getByText("Post1s Content")).toBeInTheDocument();

    expect(
      screen.queryByRole("heading", { name: /Post List/i })
    ).not.toBeInTheDocument();
  });

  it('setSelectedPost back to null: switches back to PostList when "onBack" is called', () => {
    render(<PostsPage posts={mockPostsData} />);

    const selectButton = screen.getByRole("button", { name: /Select 1/i });
    fireEvent.click(selectButton);

    expect(
      screen.getByRole("heading", { name: /Post Detail/i })
    ).toBeInTheDocument();

    const backButton = screen.getByRole("button", { name: /Close/i });
    fireEvent.click(backButton);

    expect(
      screen.getByRole("heading", { name: /Post List/i })
    ).toBeInTheDocument();
    expect(
      screen.queryByRole("heading", { name: /Post Detail/i })
    ).not.toBeInTheDocument();
  });
});

global.fetch = jest.fn();

describe("getStaticProps for PostsPage", () => {
  beforeEach(() => {
    fetch.mockClear();
  });

  it("fetches posts successfully and returns them as props", async () => {
    fetch.mockResolvedValueOnce({
      ok: true,
      json: () => Promise.resolve(mockPostsData),
    });

    const response = await getStaticProps({});

    expect(fetch).toHaveBeenCalledWith("http://localhost:3000/api/posts");

    expect(response).toEqual({
      props: {
        posts: mockPostsData,
      },
      revalidate: 10,
    });
  });

  it("returns notFound: true if the fetch fails to return posts", async () => {
    fetch.mockResolvedValueOnce({
      ok: true,
      json: () => Promise.resolve(null),
    });

    const response = await getStaticProps({});

    expect(response).toEqual({
      notFound: true,
    });
  });

  it("handles fetch network error gracefully", async () => {
    fetch.mockRejectedValueOnce(new Error("Network error"));
    await expect(getStaticProps({})).rejects.toThrow("Network error");
  });
});
