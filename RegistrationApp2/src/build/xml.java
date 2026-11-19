<?xml version="1.0" encoding="UTF-8"?>
<project name="QuickChatApp" default="run" basedir=".">

    <!-- Define directories -->
    <property name="src.dir" value="src" />
    <property name="build.dir" value="build" />
    <property name="lib.dir" value="lib" />
    
    <!-- Define the main class to run -->
    <property name="main.class" value="registrationapp2.Main" />

    <!-- Setup the classpath to include the build folder and all jars in the lib folder -->
    <path id="classpath">
        <pathelement location="${build.dir}" />
        <fileset dir="${lib.dir}">
            <include name="*/.jar" />
        </fileset>
    </path>

    <!-- Target 1: Initialize (Create build directory) -->
    <target name="init">
        <mkdir dir="${build.dir}" />
    </target>

    <!-- Target 2: Compile the Java code -->
    <target name="compile" depends="init" description="Compile all Java source files">
        <javac srcdir="${src.dir}" destdir="${build.dir}" includeantruntime="false">
            <classpath refid="classpath" />
        </javac>
        <!-- Copy JSON file to build dir so the app can find it when running -->
        <copy file="stored_messages.json" todir="${build.dir}" failonerror="false" />
    </target>

    <!-- Target 3: Run the Main Application -->
    <target name="run" depends="compile" description="Run the main application">
        <java classname="${main.class}" fork="true" dir="${build.dir}">
            <classpath refid="classpath" />
            <classpath>
                <pathelement location="." /> <!-- Allows reading JSON from root if needed -->
            </classpath>
        </java>
    </target>

    <!-- Target 4: Compile and Run Unit Tests -->
    <target name="test" depends="compile" description="Run JUnit tests">
        <junit printsummary="yes" haltonfailure="yes">
            <classpath refid="classpath" />
            <formatter type="plain" />
            
            <!-- Specify the test class -->
            <test name="registrationapp2.MessageManagerTest" />
        </junit>
    </target>

    <!-- Target 5: Clean up (Deletes the build folder) -->
    <target name="clean" description="Clean up build artifacts">
        <delete dir="${build.dir}" />
    </target>

</project>
