import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter';

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Prueba técnica Azurian",
  description: "Ejercicio prueba técnica Azurian",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  return (
    <html lang="es">
      <AppRouterCacheProvider>
        <body className={inter.className}>{children}</body>
      </AppRouterCacheProvider>
    </html>
  );
}
