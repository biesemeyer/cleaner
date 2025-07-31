# Cleaner

Utilities for use with `java.lang.ref.Cleaner`

 - `net.biesemeyer.cleaner.CleanerThreadLocal`
   - a wrapper for `java.lang.ThreadLocal` that allows registration of a cleanup action to be run against a value when it is removed (such as when the thread has finished).