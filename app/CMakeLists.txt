

cmake_minimum_required(VERSION 3.4.1)



add_library(bspatch-lib
             SHARED
             src/main/cpp/bspatch.c
            )

find_library(log-lib
              log )


target_link_libraries(bspatch-lib
                       ${log-lib} )
