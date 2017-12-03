#!/usr/bin/env bash

echo "Deploying code signing key..."

cd ./.travis || exit

openssl aes-256-cbc -K "$encrypted_2fd42dde28d0_key" -iv "$encrypted_2fd42dde28d0_iv" -in codesigning.asc.enc -out codesigning.asc -d
gpg --fast-import codesigning.asc

cd ../ || exit
