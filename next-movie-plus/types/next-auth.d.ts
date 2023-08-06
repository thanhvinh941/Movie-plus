import NextAuth from "next-auth/next";

declare module "next-auth" {
  interface Session {
    user: {
      id: String;
      username: String;
      fName: String;
      lName: String;
      email: String;
      imageUrl: String;
      accessToken: string;
      accessTokenExpiry: number;
      refreshToken: string;
    };
  }
}
