#!/usr/bin/env bash
context=$1;
git pull origin worm;
git add .
git commit -m "commit by: $context"
git push origin worm
