#!/bin/bash
echo --------------------------------------------------------------------------
echo -e "Running server"
echo -e "Project will be available at http://localhost:5000"
echo -e "\n"
read -n 1 -s -p "Press any key to continue: "
echo -e "\n"
echo --------------------------------------------------------------------------
heroku local web
