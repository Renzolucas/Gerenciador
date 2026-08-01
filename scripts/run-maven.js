const { spawn } = require("node:child_process");

const isWindows = process.platform === "win32";

const command = isWindows ? "mvnw.cmd" : "./mvnw";

const args = process.argv.slice(2);

const child = spawn(command, args, {
  cwd: process.cwd(),
  stdio: "inherit",
  shell: true,
});

child.on("exit", (code) => {
  process.exit(code);
});
