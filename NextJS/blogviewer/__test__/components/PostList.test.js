import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom";
import PostList from "../../src/components/PostList";

const mockPosts = [
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

describe("PostList Component", () => {
  it("renders all post details correctly for each post", () => {
    const handlePostSelect = jest.fn();
    render(<PostList posts={mockPosts} onPostSelect={handlePostSelect} />);
    expect(screen.getByText("1. Post1")).toBeInTheDocument();
    expect(screen.getByText("By A1")).toBeInTheDocument();
    expect(screen.getByText("On 2025-07-01")).toBeInTheDocument();

    expect(screen.getByText(/Post1s Content/)).toBeInTheDocument();

    expect(screen.getByText("2. Post2")).toBeInTheDocument();
    expect(screen.getByText("By A2")).toBeInTheDocument();
    expect(screen.getByText("On 2025-07-02")).toBeInTheDocument();
  });

  it('calls onPostSelect with the correct post when "Read More" is clicked', () => {
    const handlePostSelect = jest.fn();
    render(<PostList posts={mockPosts} onPostSelect={handlePostSelect} />);

    const readMoreButtons = screen.getAllByRole("button", {
      name: /Read More/i,
    });

    fireEvent.click(readMoreButtons[0]);

    expect(handlePostSelect).toHaveBeenCalledTimes(1);
    expect(handlePostSelect).toHaveBeenCalledWith(mockPosts[0]);

    fireEvent.click(readMoreButtons[1]);

    expect(handlePostSelect).toHaveBeenCalledTimes(2);
    expect(handlePostSelect).toHaveBeenCalledWith(mockPosts[1]);
  });

  it('renders a "No posts available" message when the posts array is empty', () => {
    render(<PostList posts={[]} onPostSelect={() => {}} />);
    expect(screen.getByText("No posts available.")).toBeInTheDocument();
  });

  it('renders a "No posts available" message when posts prop is null', () => {
    render(<PostList posts={null} onPostSelect={() => {}} />);
    expect(screen.getByText("No posts available.")).toBeInTheDocument();
  });

  it("verifies the content truncation logic", () => {
    const longContent =
      "This is a very long string designed to test the truncation functionality. It needs to be over two hundred characters long to make sure that the slice method is working as intended. We will keep adding text until we are absolutely sure it has surpassed the limit.".repeat(
        3
      );
    const shortContent = "This is short.";

    const postsWithVariedContent = [
      {
        id: 1,
        title: "Long Post",
        content: longContent,
        author: "Tester",
        date: "2025-01-01",
      },
      {
        id: 2,
        title: "Short Post",
        content: shortContent,
        author: "Tester",
        date: "2025-01-01",
      },
    ];

    render(<PostList posts={postsWithVariedContent} onPostSelect={() => {}} />);

    const expectedTruncated = longContent.slice(0, 200) + "...";
    expect(screen.getByText(expectedTruncated)).toBeInTheDocument();

    const expectedShort = shortContent + "...";
    expect(screen.getByText(expectedShort)).toBeInTheDocument();
  });
});
