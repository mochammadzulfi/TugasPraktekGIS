import mapnik
m = mapnik.Map(1024,768)
m.background = mapnik.Color('steelblue')
s = mapnik.Style()
r = mapnik.Rule()
polygon_symbolizer = mapnik.PolygonSymbolizer()
polygon_symbolizer.fill = mapnik.Color('#f2eff9')
r.symbols.append(polygon_symbolizer)

line_symbolizer = mapnik.LineSymbolizer()
line_symbolizer.stroke = mapnik.Color('rgb(0%,100%,0%)')

r.symbols.append(line_symbolizer)

#nama negara
# basinsLabels = mapnik.TextSymbolizer(mapnik.Expression('[NAME]'), 'DejaVu Sans Bold',2,mapnik.Color('black'))
# basinsLabels.halo_fill = mapnik.Color('yellow')
# basinsLabels.halo_radius = 2
# r.symbols.append(basinsLabels)


point_sym = mapnik.PointSymbolizer()
point_sym.allow_overlap = True
r.symbols.append(point_sym)

s.rules.append(r)

#AUSTRALIA
highlight = mapnik.PolygonSymbolizer()
highlight.fill = mapnik.Color('red')
australia = mapnik.Rule()
australia.filter = mapnik.Expression("[NAME] = 'Australia'")
australia.symbols.append(highlight)
s.rules.append(australia)
m.append_style('My Style',s)
ds = mapnik.Shapefile(file="Natural_Earth/ne_110m_admin_0_countries.shp")
layer = mapnik.Layer('world5')
layer.datasource = ds 
layer.styles.append('My Style')
m.layers.append(layer)

s = mapnik.Style()
r = mapnik.Rule()

#JEPANG
line_symbolizer = mapnik.LineSymbolizer()
line_symbolizer.stroke = mapnik.Color('rgb(0%,0%,100%)')
r.symbols.append(line_symbolizer)
s.rules.append(r)
m.append_style('My Style2',s)
ds = mapnik.Shapefile(file="gis/JPN/JPN_adm0.shp")
layer = mapnik.Layer('japan')
layer.datasource = ds 
layer.styles.append('My Style2')
m.layers.append(layer) 
s = mapnik.Style()
r = mapnik.Rule()

#INDONESIA
line_symbolizer = mapnik.LineSymbolizer()
line_symbolizer.stroke = mapnik.Color('rgb(0%,100%,100%)')
r.symbols.append(line_symbolizer)
s.rules.append(r)
m.append_style('My Style3',s)
ds = mapnik.Shapefile(file="gis/IDN/IDN_adm0.shp")
layer = mapnik.Layer('indonesia')
layer.datasource = ds 
layer.styles.append('My Style3')
m.layers.append(layer)
s = mapnik.Style()
r = mapnik.Rule()

#CHINA
line_symbolizer = mapnik.LineSymbolizer()
line_symbolizer.stroke = mapnik.Color('rgb(100%,100%,0%)')
r.symbols.append(line_symbolizer)
s.rules.append(r)
m.append_style('My Style4',s)
ds = mapnik.Shapefile(file="gis/CHN/map.shp")
layer = mapnik.Layer('china')
layer.datasource = ds 
layer.styles.append('My Style4')
m.layers.append(layer)
s = mapnik.Style()
r = mapnik.Rule()

#TURKEY
line_symbolizer = mapnik.LineSymbolizer()
line_symbolizer.stroke = mapnik.Color('rgb(0%,0%,0%)')
r.symbols.append(line_symbolizer)
s.rules.append(r)
m.append_style('My Style5',s)
ds = mapnik.Shapefile(file="gis/india/map.shp")
layer = mapnik.Layer('india')
layer.datasource = ds 
layer.styles.append('My Style5')
m.layers.append(layer)
s = mapnik.Style()
r = mapnik.Rule()

m.zoom_all()
mapnik.render_to_file(m,'world.pdf', 'pdf')
print "rendered image to 'world.pdf'"