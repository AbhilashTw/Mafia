ant ci-setup runTest > antlog.txt
mv antlog.txt reports/antlog.txt
ant archive
echo "Build Over"