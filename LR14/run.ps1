param(
    [ValidateSet('Menu', 'Demo', 'Examples', 'Build')]
    [string]$Mode = 'Menu'
)

$ErrorActionPreference = 'Stop'
Push-Location $PSScriptRoot
try {
    New-Item -ItemType Directory -Force 'target\classes' | Out-Null
    $lr14Sources = @(Get-ChildItem 'src\main\java' -Recurse -Filter '*.java' | ForEach-Object FullName)
    & javac --release 8 -encoding UTF-8 -d 'target\classes' $lr14Sources
    if ($LASTEXITCODE -ne 0) { throw 'Java compilation failed.' }
    switch ($Mode) {
        'Menu' { & java '-Dstdout.encoding=UTF-8' '-Dstderr.encoding=UTF-8' -cp 'target\classes' org.example.App }
        'Demo' { & java '-Dstdout.encoding=UTF-8' '-Dstderr.encoding=UTF-8' -cp 'target\classes' org.example.App --demo }
        'Examples' { & java '-Dstdout.encoding=UTF-8' '-Dstderr.encoding=UTF-8' -cp 'target\classes' org.example.examples.TheoryExamples }
    }
    if ($LASTEXITCODE -ne 0) { throw 'Java execution failed.' }
} finally {
    Pop-Location
}
