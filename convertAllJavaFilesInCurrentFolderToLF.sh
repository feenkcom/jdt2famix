
find . -name "*.java" -type f -exec perl -pi -e 's/\r\n/\n/;' $* {} \;
