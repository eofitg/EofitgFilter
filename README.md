# Eofitg Filter

`1.8.9-forge-mod`

A very simple chat filter mod — just matching and ignoring.

## Features

- Automatically decodes Minecraft formatting codes

- Supports **wildcard** matching

- Supports **regex expression** matching

## Commands

`/eofitgfilter <option>` or `/eof <option>`

- `toggle` – enable/disable the filter
- `reload` – reload the configuration file

## Configuration

`eofitg-filter.json`

Example for ignoring messages like  
`§l§a{player_name} §r§fjoined the Lobby §c{lobby_id}§f!`

```json5
[
    {
        "message": "* joined the Lobby *!", 
        "regex": false
    }, 
    // or 
    {
        "message": "[\w_]+ joined the Lobby [\d]+!", 
        "regex": true
    } 
]
```

Since formatting codes are decoded automatically, you can also write them explicitly:

```json5
[
    {
        "message": "§l§a* §r§fjoined the Lobby §c*§f!", 
        "regex": false
    }, 
    // or 
    {
        "message": "§l§a[\w_]+ §r§fjoined the Lobby §c[\d]+§f!", 
        "regex": true
    } 
]
```