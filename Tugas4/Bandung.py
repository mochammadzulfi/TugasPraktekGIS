import mapnik
m = mapnik.Map(800,600)
m.background = mapnik.Color('steelblue')
s = mapnik.Style()
r = mapnik.Rule()
polygon_symbolizer = mapnik.PolygonSymbolizer()
polygon_symbolizer.fill = mapnik.Color('#06D109')
r.symbols.append(polygon_symbolizer)

line_symbolizer = mapnik.LineSymbolizer()
line_symbolizer.stroke = mapnik.Color('rgb(50%,50%,50%)')


r.symbols.append(line_symbolizer)
s.rules.append(r)
m.append_style('My Style', s)
ds = mapnik.Shapefile(file="gis/BANDUNG/BANDUNG.shp")
layer = mapnik.Layer('World')
layer.datasource = ds
layer.styles.append('My Style')
m.layers.append(layer)
m.zoom_all()
mapnik.render_to_file(m, 'Bandung.jpeg', 'jpeg')
print "rendered image to 'Bandung.jpeg'"
