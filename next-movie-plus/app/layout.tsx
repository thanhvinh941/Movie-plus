import { ReactNode } from "react"
import Providers from "./provider";
import AppBar from "./appBar";

interface RootLayoutProps{
  children: ReactNode;
}

export default function RootLayout({children}: RootLayoutProps) {
  return (
    <html lang="en">
      <body>
        <Providers>
          <AppBar />
          {children}
        </Providers>
        </body>
    </html>
  )
}