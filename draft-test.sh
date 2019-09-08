executable="target/agile-engine-1.0-jar-with-dependencies.jar"

test0=$(java -jar "$executable" src/test/data/sample-0-origin.html src/test/data/sample-0-origin.html)

if [ "$test0" = "/html/body" ]
  then printf "test0 success\n"
  else printf "test0 failed. returned \"%s\"\n" "$test0"
fi


test1=$(java -jar "$executable" src/test/data/sample-0-origin.html src/test/data/sample-1-evil-gemini.html)

if [ "$test1" = "/html/body" ]
  then printf "test1 success\n"
  else printf "test1 failed. returned \"%s\"\n" "$test1"
fi

test2=$(java -jar "$executable" src/test/data/sample-0-origin.html src/test/data/sample-2-container-and-clone.html)
if [ "$test2" = "/html/body" ]
  then printf "test2 success\n"
  else printf "test2 failed. returned \"%s\"\n" "$test2"
fi

test3=$(java -jar "$executable" src/test/data/sample-0-origin.html src/test/data/sample-3-the-escape.html)
if [ "$test3" = "/html/body" ]
  then printf "test3 success\n"
  else printf "test3 failed. returned \"%s\"\n" "$test3"
fi

test4=$(java -jar "$executable" src/test/data/sample-0-origin.html src/test/data/sample-4-the-mash.html)
if [ "$test4" = "/html/body" ]
  then printf "test4 success\n"
  else printf "test4 failed. returned \"%s\"\n" "$test4"
fi
