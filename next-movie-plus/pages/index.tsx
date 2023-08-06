import { signIn, signOut } from 'next-auth/react';

export default function Home() {
  return (
    <>
      <button onClick={() => signIn('credentials', { email: 'example@example.com', password: 'example' })}>
        Sign in
      </button>
      <button onClick={() => signOut()}>
        Sign out
      </button>
    </>
  )
}