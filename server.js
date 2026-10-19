// Bridge between the browser and your Java program.
// It does NOT contain any study logic - it only passes text back and forth.
import express from "express";
import { WebSocketServer } from "ws";
import { spawn } from "child_process";
import http from "http";
import path from "path";
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const app = express();
app.use(express.static(path.join(__dirname, "public"))); // serves index.html, style.css, script.js
const server = http.createServer(app);
const wss = new WebSocketServer({ server });

// Every browser tab gets its own copy of your Java program.
wss.on("connection", (ws) => {
  const java = spawn(
    "java",
    ["-Dfile.encoding=UTF-8", "-Dstdout.encoding=UTF-8", "StudyHelper"],
    { cwd: path.join(__dirname, "java-app") }
  );

  java.stdout.on("data", (d) => ws.send(d.toString()));   // Java output -> browser
  java.stderr.on("data", (d) => ws.send(d.toString()));
  java.on("error", (e) =>
    ws.send("[bridge] Could not start Java: " + e.message +
            "\nDid you run 'javac StudyHelper.java' inside java-app?\n"));
  java.on("exit", () => ws.send("\n[session ended - refresh the page to start again]\n"));

  ws.on("message", (m) => {                                // browser input -> Java
    if (java.stdin.writable) java.stdin.write(m.toString() + "\n");
  });
  ws.on("close", () => java.kill());
});

server.listen(3000, () => console.log("Open http://localhost:3000"));
