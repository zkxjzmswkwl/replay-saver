module mouse;

import core.sys.windows.windows;
import std.stdio;
import std.conv;
import core.thread;

void main() {
    POINT cursorPos;
    for (;;) {
        if (GetAsyncKeyState('K')) {
            if (GetCursorPos(&cursorPos)) {
                writeln(cursorPos.x.to!string ~ ":" ~ cursorPos.y.to!string);
                Thread.sleep(dur!("msecs")(500));
            }
        }
    }
}