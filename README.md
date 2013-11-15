keeps-characterization-cdr
==========================

Characterization tool for Corel Draw files, made by KEEP SOLUTIONS.


## Build & Use

To build the application simply clone the project and execute the following Maven command: `mvn clean package`  
The binary will appear at `target/cdr-characterization-tool-1.0-SNAPSHOT-jar-with-dependencies.jar`

To see the usage options execute the command:

```bash
$ java -jar target/cdr-characterization-tool-1.0-SNAPSHOT-jar-with-dependencies.jar -h
usage: java -jar [jarFile]
 -f <arg> file to analyze
 -h       print this message
 -v       print this tool version
```

## Tool Output Example
```bash
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<cdrCharacterizationResult>
    <features>
        <item>
            <key>Artifacts.verbose</key>
            <value>true</value>
        </item>
        <item>
            <key>Background_color</key>
            <value>white</value>
        </item>
        <item>
            <key>Base_type</key>
            <value>Bilevel</value>
        </item>
        <item>
            <key>Border_color</key>
            <value>rgba(223,223,223,1)</value>
        </item>
	...
    </features>
    <valid>true</valid>
</cdrCharacterizationResult>
```

## License

This software is available under the [LGPL version 3 license](LICENSE).

