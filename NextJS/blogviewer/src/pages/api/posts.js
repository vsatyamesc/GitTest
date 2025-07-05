export default function handler(req, res) {
  const posts = [
    {
      id: 1,
      title: "The Rise of Decentralized AI: A Glimpse Into the Future",
      content:
        "In recent years, the buzz around decentralization has grown far beyond cryptocurrencies. With the increasing concern over data privacy and corporate monopolies, decentralized AI has emerged as a powerful alternative. These systems distribute computation and model training across a network of nodes, ensuring transparency, security, and resilience. From federated learning in healthcare to blockchain-integrated AI governance, this paradigm shift empowers users without compromising performance. In this post, we explore real-world applications, current limitations, and what the next decade might look like for decentralized intelligence.",
      author: "Satyam",
      date: "July 4 2025, Thursday",
    },
    {
      id: 2,
      title:
        "Mastering the Art of Digital Minimalism in a Hyperconnected World",
      content:
        "As screen time surges, digital burnout has become a silent epidemic. Digital minimalism—cutting out digital clutter to focus on meaningful tech use—offers a remedy. It’s not about abandoning technology but about reclaiming attention and intention. This post walks through strategies like the “30-day digital declutter,” evaluating essential tools, setting screen boundaries, and rediscovering offline joys. We also examine how companies are designing less intrusive tech and why this movement is gaining momentum among creators, students, and professionals alike.",
      author: "Satyam",
      date: "July 4 2025, Thursday",
    },
    {
      id: 3,
      title: "From Zero to SaaS: Building a Startup with No Code",
      content:
        "Gone are the days when launching a tech startup required deep coding skills. With no-code platforms like Bubble, Webflow, and Airtable, solopreneurs and small teams are building full-fledged SaaS businesses from scratch. This guide shares a step-by-step journey: idea validation, building MVPs without writing a line of code, scaling operations, and even managing payments and users. We also include interviews with 3 founders who made over $10K/month using no-code tools alone.",
      author: "Satyam",
      date: "July 4 2025, Thursday",
    },
  ];
  res.status(200).json(posts);

  // setTimeout(() => {
  //   res.status(200).json(posts);
  // }, 2000);
}
