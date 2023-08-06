import { useSession } from "next-auth/react";

export default function AppBar() {
  const { data: session } = useSession();

  return (
    <>
      this is navbar
      <nav>
        <ul></ul>
      </nav>
    </>
  );
}
