#!/usr/bin/env bash
set -e
set -o pipefail

lein vcs assert-committed
BRANCH=$(git rev-parse --abbrev-ref HEAD)
VERSION_WITH_QUOTES=$(grep "defproject" < project.clj | cut -d' ' -f3)
VERSION="${VERSION_WITH_QUOTES//\"}"
IMAGE=roosta/sh.roosta.gallery:$VERSION

if [[ "$BRANCH" != "master" ]]; then
    echo "Only deploy from master!";
    exit 1;
fi

echo "Building image $IMAGE ..."
lein prod

echo "Building docker image..."
docker build -t $IMAGE .

echo "Uploading docker image..."
docker push $IMAGE

echo "Done! published $IMAGE successfully"
