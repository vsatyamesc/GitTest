import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom";
import PostDetail from "../../src/components/PostDetail";

const mockPost = {
  id: 1,
  title: "Detailed Post",
  content: "This is the content of the detailed post.",
};

describe("PostDetail", () => {
  it("renders post details correctly", () => {
    render(<PostDetail post={mockPost} onBack={() => {}} />);

    expect(screen.getByText("Detailed Post")).toBeInTheDocument();
    expect(
      screen.getByText("This is the content of the detailed post.")
    ).toBeInTheDocument();
  });

  it("calls onBack when the back button is clicked", () => {
    const handleBack = jest.fn();
    render(<PostDetail post={mockPost} onBack={handleBack} />);

    fireEvent.click(screen.getByLabelText("Back to post list"));
    expect(handleBack).toHaveBeenCalledTimes(1);
  });

  it("does not render if no post is provided", () => {
    const { container } = render(<PostDetail post={null} onBack={() => {}} />);
    expect(container.firstChild).toBeNull();
  });
});
